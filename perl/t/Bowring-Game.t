package t::Bowling::Game;
use v5.18;
use warnings;

use parent qw(Test::Class);
use Test::More;

sub _require : Test(startup => 1) {
    require_ok('Bowling::Game');
}

sub gutter_game : Tests {
    my $game = Bowling::Game->new;
    isa_ok $game, 'Bowling::Game';

    for my $i (0..19) {
        $game->role(0);
    }
    is $game->score, 0, 'score of gutter game is zero';
}

Test::Class->runtests;

1;
