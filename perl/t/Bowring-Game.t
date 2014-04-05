package t::Bowling::Game;
use v5.18;
use warnings;

use parent qw(Test::Class);
use Test::More;

sub _require : Test(startup => 1) {
    require_ok('Bowling::Game');
}

sub _prepare_game : Test(setup => 1) {
    my ($self) = @_;

    $self->{game} = Bowling::Game->new;
    isa_ok $self->{game}, 'Bowling::Game';
}

sub gutter_game : Tests {
    my ($self) = @_;
    my $game = $self->{game};

    for my $i (0..19) {
        $game->role(0);
    }
    is $game->score, 0, 'score of gutter game is zero';
}

sub all_one_game : Tests {
    my ($self) = @_;
    my $game = $self->{game};

    for my $i (0..19) {
        $game->role(1);
    }
    is $game->score, 20, 'score of all 1 game is 20';
}

Test::Class->runtests;

1;
